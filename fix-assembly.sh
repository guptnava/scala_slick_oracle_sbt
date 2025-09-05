#!/bin/bash
set -e

JAR=target/scala-2.10/hello-slick-oracle-assembly-0.1.0.jar
TMPDIR=assembly_tmp
FIXED_JAR=target/scala-2.10/hello-slick-oracle-fixed.jar

# 1. Clean temp dir
rm -rf "$TMPDIR"
mkdir -p "$TMPDIR"

# 2. Extract original assembly JAR
echo "Extracting $JAR..."
cd "$TMPDIR"
jar xf "../$JAR"
cd ..

# 3. Remove oracle classes
echo "Removing Oracle classes from assembly..."
rm -rf $TMPDIR/oracle

# 4. Repack into a new jar
echo "Repacking into $FIXED_JAR..."
cd "$TMPDIR"
jar cf "../$FIXED_JAR" .
cd ..

# 5. Show what's inside (verify Oracle is gone)
echo "Contents of fixed jar:"
jar tf "$FIXED_JAR" | grep oracle || echo "✅ No Oracle classes inside"

echo "Done. Use this command to run:"
echo "java -cp \"$FIXED_JAR:lib/*\" Main"

